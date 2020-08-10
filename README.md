# xml-validator

XML validáló alkalmazás meglévő XSD alapján

- A megadott .xsd és a 3 db valid illetve nem valid .xml kiterjesztésű fájl megtalálható az xml-files könyvtárban

Használat:
- javac XMLValidator.java
- java XMLValidator "C:/xml-t_xsd-t_tartalmazo_konyvtar/" "hasznalni_kivant_schema.xsd"

pl: 

java XMLValidator "/xml-validator/xml-files" "schema.xsd"
