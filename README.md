# AppSecEU2016

This github repository contains all demos
for [AppSecEU 2016](http://2016.appsec.eu) in Rome.

## Five minute security
This module shows a basically automatically generated secure web application
using spring boot and spring security. This has been generated
via [start.spring.io](http://start.spring.io). Only user password has been
configured afterwards to avoid copying generated password after each server start.

## Advanced security
This module shows a bit more _real-world_ security configuration made of...

* ...persistent user (JPA entity) with username and password
* ...UserDetailsService to connect spring security to persistent user entity
* ...PasswordEncoder to encrypt and check the user password using BCrypt

## Authorizations demo
This module shows how to add the following features to basic 5 minute security module:

* A login form instead of basic authentication
* Additional method layer security checking
* Corresponding integration tests for authorization and login

## CSRF demo
This module shows how to protect requests against cross site request forgery (CSRF) attacks.
All POST requests are protected automatically by CSRF tokens when using [Thymeleaf](http://www.thymeleaf.org/)
together with spring security.
To protect GET requests the CSRF token has to be added as additional request parameter manually or even better
only use POST requests for all modifying requests!

## Security header
This module shows which security headers are supported out of the box by spring security:

* Cache control
* X-Content-Type-Options: nosniff (enabled by default)
* Strict-Transport-Security (enabled when HTTPS is active)
* HTTP Public Key Pinning (new in spring security 4.1, disabled by default)
* X-Frame-Options (enabled by default)
* X-XSS-Protection (enabled by default)
* Content Security Policy (new in spring security 4.1, disabled by default)
* Custom headers (user defined)

## Tweetable OAuth2
This module just shows how easy it is to implement a very basic
authorization/resource server. This can be used to get an oauth bearer token
via password grant type.

## OAuth demos
The following modules show the different parties in OAuth2 authorization workflow.
### Authorization server
This authorization server supports all grant types like authorization code, implicit or password.
Additionally it shows a login form.
### Resource server
This resource server provides a simple restful service that is protected
and can only be accessed using a valid oauth access token.
### Client
This is the client part that calls the provided restful service of the resource server and
requests an access token from authorization server.
