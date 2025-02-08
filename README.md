# try_TSV

## 環境構築

### Maven

- [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

```shell
$ mvn --version
Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
Maven home: /opt/apache-maven-3.9.9
Java version: 17.0.13, vendor: Ubuntu, runtime: /usr/lib/jvm/java-17-openjdk-arm64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "5.15.153.1-microsoft-standard-wsl2", arch: "aarch64", family: "unix"
```

### Create Project

```shell
$ mvn archetype:generate \
-DgroupId=com.github.gbz3 \
-DartifactId=try-TSV \
-DarchetypeArtifactId=maven-archetype-quickstart \
-DarchetypeVersion=1.5 \
-DinteractiveMode=false
```

### Add jqwik

- [How to Use](https://jqwik.net/docs/current/user-guide.html#how-to-use)
- [Writing Properties](https://jqwik.net/docs/current/user-guide.html#writing-properties)





