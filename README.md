Crafter CMS Test Suite
=========================

Crafter CMS Test Suite is the test harness for Crafter CMS.

Testing enviroment configuration
====

A file with this path `PROJECT-ROOT/test-properties.properties` has to be created before 
launching the test suite `mvn clean test`. Valid properties are:

```properties
webBrowser=chrome|firefox|remote
firefox.driver.path=PATH-TO-BIN
chrome.driver.path=PATH-TO-BIN
studio.base.url = http://localhost:8080/studio#/login
delivery.base.url= http://localhost:9080/
selenium.hub.url = http://localhost:4444/wd/hub
selenium.remote.browser = chrome
```

`selenium.hub.url` and `selenium.remote.browser` are needed when `webBrowser` is set to `remote`. 
`firefox.driver.path` and `chrome.driver.path` are needed only when `webBrowser` is `firefox` or `chrome` 