package fr.gstraymond.script
import fr.gstraymond.oracle.importer.Importer

def path = 'src/main/resources/oracle/'

/** from http://www.yawgatog.com/resources/oracle/ */
def fileName = 'All Sets-2014-05-03.txt' 
//def fileName = 'test.txt'

new BaseImporter( 
	enableDebug: false, 
	enableIndex: true, 
	clearConfigure: true, 
	cards: new Importer().parseCards(path, fileName)
).launch()
