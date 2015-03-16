# About #
There are some old HTML pages that achieve extended range of characters using Internet Explorer-only hack of putting  other characters inside `<font face="Symbol">` tag.

This method is non-standard and does not not work in any browser other than Internet Explorer (and Netscape Navigator - versions 4.x and older). Thankfully nowadays we have much better ways of achieving the goal of using extended set of characters on a webpage.

**Symbol Defacer** converts characters embedded using the `<font face="Symbol">` method to proper, cross-browser Unicode.

# Usage #
**Symbol Defacer** is a command line tool written in Java. You should [download it](http://code.google.com/p/symbol-defacer/downloads/detail?name=symbol-defacer-0.1.zip) and run the jar:

```
java -jar defacer.jar directory
```

where "directory" is a path to a directory with HTML files to convert. It may also be a path to a single HTML file.