Crafter CMS Test Suite
=========================

Crafter CMS Test Suite is the test harness for Crafter CMS.

Testing enviroment configuration
====

A file with this path `PROJECT-ROOT/test-properties.properties` has to be created before 
launching the test suite `mvn clean test`. Valid properties are:

```properties
webBrowser = chrome|firefox|remote
firefox.driver.path = PATH-TO-BIN
chrome.driver.path = PATH-TO-BIN
studio.base.url = http://localhost:8080/studio#/login
delivery.base.url= http://localhost:9080/
selenium.hub.url = http://localhost:4444/wd/hub
selenium.remote.browser = chrome
```

`selenium.hub.url` and `selenium.remote.browser` are needed when `webBrowser` is set to `remote`. 
`firefox.driver.path` and `chrome.driver.path` are needed only when `webBrowser` is `firefox` or `chrome`

Selenium-Grid with docker-compose
====
We can run the tests in a Selenium-Grid using Selenium Docker images and a basic hub/node configuration
with docker-compose

Open a shell, navigate to the folder [selenium_grid](selenium_grid) and use the command `docker-compose up -d` 
to start up the selenium-grid.

Chrome browsers containers can scale by using `docker-compose --scale chrome=4 -d`. We now have 4 chrome browsers
ready to be used.Firefox test also can be scaled using the respective service in the 
*docker-compose* command: `docker-compose scale firefox=5 -d`

When the grid is up and ready the values `webBrowser`, `selenium.hub.url` and `selenium.remote.browser`should be set. 


Access localhost(authoring, delivery) from selenium-grid containers
====
The ip value of the docker network interface should be used. In Linux this values defaults to `172.17.0.1` you can 
check the value with the command `ip a | grep docker`.

The value of `studio.base.url` should be `http://172.17.0.1:8080/studio#/login` while for delivery
the property is `delivery.base.url = http://172.17.0.1:9080/` 

In the case of running the selenium-grid in a MAC computer, accessing the host from the containers is possible 
with the hostname  `host.docker.internal`. The hostname `host.docker.internal` is not resolved from outside of a
container, to be able to call API endpoint from the test machine to this hostname a entry in the `/etc/host`
in the MAC computer should be added.

>127.0.0.1 host.docker.internal

So accessing `host.docker.internal` from the test machine or the browser running in a container will always resolve
to localhost.