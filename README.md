# SpringAuthorizationServer
authorization server for SSO


IN order to start it on local machine need to overwrite 127.0.0.1 as auth-server
on local machine
On windows : "C:\Windows\System32\drivers\etc\hosts" open in notepad and write a line
127.0.0.1 auth-server
downd here .

Next need to create a database
Named authorizationserverstorage
username: postgres
password: root

Next when sso server started in order to connect client application here need to
have application.yml on client's side like this

server:
port: 8080

spring:
security:
oauth2:
client:
registration:
springoauthclient:
provider: spring
client-id: client
client-secret: secret
scope: openid, profile, user.read
authorization-grant-type: authorization_code
client-authentication-method: client_secret_basic
redirect-uri: http://127.0.0.1:8080/login/oauth2/code/spring
provider:
spring:
issuer-uri: http://auth-server:9000
messages:
base-uri: http://127.0.0.1:8081


#   where base-uri: http://127.0.0.1:8081  -- resource's server and some request redirects to this URL (see DefaultController)


meanwhile if we want to includer resource server
application.yml configuration must be :

spring:
security:
oauth2:
resourceserver:
jwt:
issuer-uri: http://auth-server:9000