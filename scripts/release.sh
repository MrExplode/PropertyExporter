cd ..
mvn clean release:prepare -P release -Dgpg.passphrase=$GPG_PASSPHRASE -Dgithub.token=$GITHUB_TOKEN
mvn release:perform -P release -Dgpg.passphrase=$GPG_PASSPHRASE -Dgithub.token=$GITHUB_TOKEN
read -n 1 -s -r -p "Press any key to continue"
