# By default 'env' value = "chromeLocal" (can be changed in 'pom.xml' file)
# Run tests using a specific browser and env:
    Run tests with Chrome on local:
        mvn clean test -Denv="chromeLocal"
    Run tests with Chrome on remote:
        mvn clean test -Denv="chromeRemote"

    Run tests with Firefox on local:
        mvn clean test -Denv="firefoxLocal"
    Run tests with Firefox on remote:
        mvn clean test -Denv="firefoxRemote"

    Run tests with Safari on local:
        mvn clean test -Denv="safariLocal"
    Run tests with Safari on remote:
        mvn clean test -Denv="safariRemote"

