#!/bin/bash

HOST_IP=$1
HOST_USERNAME=$2
HOST_PASSWORD=$3
FTP_ROOT=$4
echo "HOST_IP="$HOST_IP
echo "HOST_USERNAME="$HOST_USERNAME
echo "HOST_PASSWORD="$HOST_PASSWORD
echo "FTP_ROOT="$FTP_ROOT

USERHOME=/home/user

expect -c "
spawn ssh ${HOST_USERNAME}@${HOST_IP}
expect {
\"*yes/no\" { send \"yes\r\"; exp_continue }
\"*password:\" { send \"${HOST_PASSWORD}\r\" }
}
expect \"#*\"
send \"mkdir -p ${USERHOME}\r\"
send \"mkdir -p ${FTP_ROOT}\r\"
send \"if egrep '^CONSUMER' /etc/group >& /dev/null; then echo 'group CONSUMER already exists!'; else groupadd CONSUMER; fi\r\"
send \"if egrep '^UDSP' /etc/passwd >& /dev/null; then echo 'user UDSP already exists!'; else useradd -d ${USERHOME}/UDSP -m UDSP -p `openssl passwd -crypt 000000`; usermod -a -G CONSUMER UDSP; fi\r\"
send \"if egrep '^udspadmin' /etc/passwd >& /dev/null; then echo 'user udspadmin already exists!'; else useradd -d ${USERHOME}/udspadmin -m udspadmin -p `openssl passwd -crypt 000000`; mkdir -p ${FTP_ROOT}/udspadmin; chown udspadmin:CONSUMER ${FTP_ROOT}/udspadmin; chmod -R 770 ${FTP_ROOT}/udspadmin; fi\r\"
send \"exit\r\"
expect eof"

exit 0