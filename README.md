Content Builder
===============

[![](https://jitpack.io/v/Contargo/content-builder.svg)](https://jitpack.io/#Contargo/content-builder)

A small library that provides an easy to use API, supporting creation of
content, in services and back-ends, intended for end-user consumption.

In short - helps you create content in a common format, for web and mobile
clients.

Gettings started
----------------

Using `content-builder` in your project, as a library, means simply including
it as a Maven dependency. We recommend using [Jitpack](https://jitpack.io) to
resolve the dependency:

```xml
<dependency>
  <!-- From Jitpack-repo -->
  <groupId>com.github.Contargo</groupId>
  <artifactId>content-builder</artifactId>
  <version>${SOME-TAG}</version>
</dependency>
```

## General Idea ##

It might sometimes be necessary to create content, for publishing or persisting,
inside a business services or back-end. This means actual readable content, and
not business or domain data. For example creating a message, a note or an event
clarification, that is intended for actual end-users to read.

In order to do this, without coupling each new piece of information created by
the service, to custom code in the client (web/mobile) we've come up with a
small format that we recommend using.

This project provides a small library, that makes it easy to create and consume
such content by services and applications written in Java.

## Micro-Reference ##

The __COLA Common Content Format__ that we use, is easy for developers to
understand and very easy to use. Below is a small example representation in
JSON.

```javascript
{
  contents: [{
      mimeType: 'text/vnd.contargo.description',
      content: 'Kontakt'
    }, {
      mimeType: 'text/vnd.contargo.description',
      locale: 'en',
      content: 'Contact'
    }]
}
```

A collection called `contents` contains one or more content entries. The
required attributes `mimeType` and `content` define the actual information,
with the option to define localized entries using a `locale` attribute.

For more information please see the project Java API documentation and the
available guide- or reference information.

### Vendor Mime/Media-Types ###

Our current choice of content type specification, is to extend on the style and
format of media- or MIME type definitions.

For example `text/vnd.contargo.description` is defined from the pattern:

    '${type}/vnd.contargo.${name}'

With the option to use subtypes and charset definitions, just as with media
types.

    '${type}/vnd.contargo.${name}+${subtype};${params}'

There's more information and concrete definitions in the project Java API
documentation.

## Known Issues

Compatibility with JSON serialization, currently assumes the use of
[Jackson Databind][1]. This might change in the future, to suite more use-
cases.

_We use `jackson-annotations` to ensure omission of empty properties when
serializing to JSON._

 [1]: https://github.com/FasterXML/jackson-databind

## Development ##

This is a pretty straight-forward Java-project, use `mvn` to build,
test and deploy. Happy hacking!
