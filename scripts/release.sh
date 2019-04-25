cd ..
mvn clean release:prepare -P release -Dgpg.passphrase=$GPG_PASSPHRASE
mvn release:perform -P release -Dgpg.passphrase=$GPG_PASSPHRASE
