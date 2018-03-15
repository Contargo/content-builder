Changelog
=========

## v0.5.0

* Add the Spring IO parent dependency as a BOM for dependencies and plugins
  used. This aligns the component for better use with projects in and around
  the Spring and Spring Boot eco-system - as is currently intended.

## v0.4.0

* Project setup updated for hosting on Github as an open source project. Adding
  and removing the necessary information and resources.

## v0.3

* Add support for mime types with `params`, this enables content entries with
  variants to be built.
* Changes the semantics for adding empty strings, trimmable empty strings and
  null values. Such content entries are now ignored.

## v0.2

* Add support for TEXT_APPICON and IMAGE_APPICON mime-types.
* Add content accessors for mime-type and locale based value retrieval.
* Add initial support for byte[] content values.

## v0.1

* Initial version with support for text content creation.
