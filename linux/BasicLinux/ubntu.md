## Ubntu

### Setting JRE/JDK
> sudo apt-get update

> sudo apt-get install default-jre

> sudo apt-get install default-jdk

### Setting Oracle JDK
> sudo add-apt-repository ppa:webupd8team/java
> sudo apt-get update

> sudo apt-get install oracle-java8-installer

### Managing Java
> sudo update-alternatives --config java

> sudo vi /etc/environment

> JAVA_HOME="/usr/lib/jvm/java-8-oracle" // write this at the end of line

> source /etc/environment

> echo $JAVA_HOME

#### reference
- https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-get-on-ubuntu-16-04

