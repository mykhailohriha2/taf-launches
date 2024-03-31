# Run tests using a specific browser:

    Run tests with Chrome:
        mvn clean test -Dbinary="chromium" -Dchannel="chrome" -Dheadless="false"
    Run tests with Firefox:
        mvn clean test -Dbinary="firefox" -Dheadless="false"
    Run tests with Safari:
        mvn clean test -Dbinary="webkit" -Dheadless="false"

# Run tests using a specific environment:

    Run tests on local:
        mvn clean test -DbaseUrl="http://localhost:8080"
    Run tests on remote:
        mvn clean test -DbaseUrl="https://rp.epam.com/"

# Run tests with Chrome on remote:

    mvn clean test -Dbinary="chromium" -Dchannel="chrome" -Dheadless="false" -DbaseUrl="https://rp.epam.com/"