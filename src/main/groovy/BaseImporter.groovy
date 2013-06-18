import java.util.concurrent.ConcurrentSkipListMap.Index;

import fr.gstraymond.elasticsearch.ESIndexer

class BaseImporter {
	
	def enableDebug = false
	def enableIndex = false
	def clearConfigure = false
	def cards = []
	
	def launch() {
		clean()
		debug()
		index()
	}
	
	def clean() {		
		// remove null elements and empty elements
		cards = cards.findAll {	it && it.title }
	}
	
	def debug() {
		if (enableDebug) {
			cards.each {
				println '----'
				println "title        ${it?.title}"
				println "colors       ${it?.colors}"
				println "CMC          ${it?.convertedManaCost}"
				println "editions     ${it?.editions}"
				println "prices       ${it?.priceRanges}"
				println "publications ${it?.publications}"
			}
		}
	}
		
	def index() {
		if (enableIndex || clearConfigure) {
			def indexer = new ESIndexer()
			indexer.clear()
			indexer.configure()
			if (enableIndex) {
				cards.eachWithIndex { card, counter ->
					println "Indexing ${counter+1}/${cards.size()} : $card.title"
					indexer.index card
				}
			}
		}
	}
}