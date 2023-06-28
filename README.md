## Local Setup Instructions

To start the application on your local machine, you need to make the following changes:

### 1. Overwrite `127.0.0.1` as the auth-server on your local machine:

On Windows, open the file `C:\Windows\System32\drivers\etc\hosts` in a text editor like Notepad and add the following line at the end of the file:

127.0.0.1 auth-server

### 2. Create a database named `authorizationserverstorage` with the following credentials:

- Username: `postgres`
- Password: `root`

### 3. Configure the client application by creating an `application.yml` file:

For the client application to connect to the SSO server, you need to have an `application.yml` file on the client's side. Use the following configuration:

```yaml
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


Where base-uri: http://127.0.0.1:8081 represents the resource server URL to which some requests will be redirected (see DefaultController).

If you also want to include a resource server, the application.yml configuration should be as follows:

Where base-uri: http://127.0.0.1:8081 represents the resource server URL to which some requests will be redirected (see DefaultController).

If you also want to include a resource server, the application.yml configuration should be as follows:
