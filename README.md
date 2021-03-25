# word-count

Generate word count statistics for a given file.

## Example

**INPUT:**

```text
Hello world & good morning. The date is 18/05/2016
```

**OUTPUT:**

```text
Word count = 9
Average word length = 4.556
Number of words of length 1 is 1
Number of words of length 2 is 1
Number of words of length 3 is 1
Number of words of length 4 is 2
Number of words of length 5 is 2
Number of words of length 7 is 1
Number of words of length 10 is 1
The most frequently occurring word length is 2, for word lengths of 4 & 5
```

## Definitions

Words recognised by this program are divided into 2 separate categories "Letter like" and "Number like".

Letter-like words contain at least 1 letter or an ampersand, and may include digits, hyphen, apostrophe and underscore.

Number-like words contain at least 1 digit, and formatted numbers such as decimal, percentage, date, time, currency and thousand separator are supported.

It is assumed that words in the target file do not span multiple lines.

## Usage

NOTE: This application requires Java 11.

Build and package the application:

```bash
mvn clean package
```

Run the application on a file (from repo directory):

```bash
java -jar target/word-count-1.0-SNAPSHOT-jar-with-dependencies.jar file.txt
```
