  * plugin accept a inputstream
  * convert the inputstream to html or xwiki code(how to address the pictures
  * then product a outputstream which contain the result html or xwiki code as a output(how to convert XHTML to wiki converter use the wikimodel XHTML to wiki syntax parser)

exmaple:

> $someDoc.setContent($officimportPlugin.convert($doc.getAttachment("...")))

> $someDoc.save()

Now $doc.setContent() only accept string