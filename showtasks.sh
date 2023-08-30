#!/usr/bin/env bash

fail() {
   echo "There were errors"
}

end() {
   echo "Safari is working"
}

if  ./runcrud.sh;  then
   open -a Safari http://localhost:8080/crud/v1/task/tasks
   end
else
   fail
fi