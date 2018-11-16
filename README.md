Crafter CMS Test Suite
=========================

Crafter CMS Test Suite is the test harness for Crafter CMS.

Testing enviroment configuration
====

A file with this path `PROJECT-ROOT/test-properties.properties` has to be created before 
launching the test suite `mvn clean test`. Valid properties are:

```properties
webBrowser=chrome|edge|ie|firefox|phantomjs
phantomjs.binary.path=PATH-TO-BIN
firefox.driver.path=PATH-TO-BIN
ie.driver.path=PATH-TO-BIN
edge.driver.path=PATH-TO-BIN
chrome.driver.path=PATH-TO-BIN
baseUrl = http://localhost:8080/studio/#/login
deliverybaseUrl=http://localhost:9080/
executionenvironment=unix|windows
```
