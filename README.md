# Swedbank csv converter to YNAB format

## Intro

This CLI program takes Swedbank statments extracted in csv format and converts them into a csv format understood by YNAB
4 (
You need a budget).

## Usage

This is a java program. You will need JAVA to be installed in your machine (version 11+). You can grab the application
from the Release section.

To execute the application run the following.

```shell
java -jar swed_to_ynab-1.0 input_file output_file
```

e.g. If the application is in a folder with your statement named input.csv you would have to run:

```shell
java -jar swed_to_ynab-1.0 input.csv output.csv
```

This would in turn create a file `output.csv` which you could then import into YNAB 4

## Building

Application is built with maven.

```shell
mvn clean package
```