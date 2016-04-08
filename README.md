Content Builder
===============

A small library that provides an easy to use API supporting creation of
content, in services and back-ends, intended for end-user consumption.

## General Idea ##

It might sometimes be necessary to create content, for publishing or persisting,
inside a business services or back-end. For example creating a JSON message or
document that is intended for actual end-users to read.

In order to do this, without coupling each new piece of information created by
the service, to custom code in the client (web/mobile) we've come up with a
small format that we can re-use.

This project provides a small Java library, that makes it easy to create
and consume such content by services and applications.

## Micro-Reference ##

The __COLA Common Content Format__ that we use is easy for developers to
understand and use. Below is a small example `JSON` representation.

```javascript
contents: [{
    mimeType: 'text/vnd.contargo.description',
    content: 'Kontakt'
  }, {
    mimeType: 'text/vnd.contargo.description',
    locale: 'en',
    content: 'Contact'
  }]
```
A collection called `contents` contains one or more content entries. The
required attributes `mimeType` and `content` define the actual information,
with the option to define localized entries using a `locale` attribute.

For more information please see the project Java API documentation and the
available guide- or reference information.

## Development ##

This is a pretty straight-forward Java-project, use `mvn` to build,
test and deploy. Happy hacking!
