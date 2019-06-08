#!/bin/bash

while printf "1. list\n2. add\n3. search\n4. remove\nq. quit\nenter option:"
read command
do
	case $command in
		1) echo "[LIST]"
		   cat "addressbook" | while read LINE
		   do
			echo "$LINE"
		   done
		   printf "\n"	
		   ;;
		2) echo "[ADD]"
		   echo "enter name:"
		   read name
		   echo "enter phone number:"
		   read number
                   
		   if [ ! -f addressbook ]
		   then
			> addressbook
		   fi

		   if grep -q "$name" "addressbook" || grep -q "$number" "addressbook"
		   then
		 	echo "name or number is duplicated"
		   else
		   	echo "$name $number" >> "addressbook"
			echo "successfully added"
		   fi

		   printf "\n"
                   ;;
		3) echo "[SEARCH]"
		   echo "enter name or number to find:"
		   read var	           
		   
		   if grep -q "$var" "addressbook"
		   then
			grep "$var" "addressbook"
			echo "successfully searched"
		   else
			echo not found
		   fi
		   printf "\n"
		   ;;
		4) echo "[REMOVE]"
		   echo "enter name or number to delete:"
		   read var
		   if grep -q "$var" "addressbook"
		   then
   			word="$(grep "$var" "addressbook")"
			echo "$word removed"
		  	mv "addressbook" tmp/addresbooktemp
		   	grep -vw "$var" "tmp/addressbooktemp" > "addressbook"
		   fi
		   printf "\n"
		   ;;
		q) break;;
		*) echo "Wrong command"
		   printf "\n"
		   ;;
	esac
done
