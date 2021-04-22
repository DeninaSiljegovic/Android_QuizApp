package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Pitanje

fun svaPitanja(): List<Pitanje> {
    return listOf(
            //pitanja za im
            Pitanje("pit1", "Koliko je 2+2?", listOf("2","18", "4"), 2),
            Pitanje("pit2", "Koliko je 930/30?", listOf("31", "30", "32"), 0),
            Pitanje("pit3", "Limes moze da ne postoji", listOf("Tacno", "Netacno"), 0),

            Pitanje("pit4", "UML je ", listOf("programski jezik", "graficki jezik", "jezik za dfeiniciju standardnih procesa"), 1),
            Pitanje("pit5", "Koji diagram prikazuju interakcije između objekata u odnosu na njihov redoslijed izvršavanja?", listOf("Dijagram aktivnosti", "Dijagram klase", "Dijagram sekvenci"), 2),
            Pitanje("pit6", "Kojem pogledu na sistem pripada dijagram aktivnosti?", listOf("Logicki pogled", "Implementacioni pogled", "Procesni pogled", "Razvojni pogled"), 2),
            Pitanje("pit7", "Diagram klasa se fokusira na to kako sistem radi?", listOf("Tacno", "Netacno"), 1)
    )
}


fun pitanjePoSifri(s: String): Pitanje {
    return svaPitanja().filter { it.naziv == s }[0]
}