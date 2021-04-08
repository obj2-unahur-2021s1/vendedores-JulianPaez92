package ar.edu.unahur.obj2.vendedores

class CentroDeDistribucion(val ciudad: Ciudad) {
    val vendedores = mutableListOf<Vendedor>()

    //NO DEVUELVE NADA
    fun agregarVendedor(algun: Vendedor) {
        if (vendedores.contains(algun)){
            throw Exception("Este changuito ya esta registrado")
        }
        else {
            vendedores.add(algun)
        }
    }
    //ETAPA 3
    fun vendedorEstrella() = vendedores.maxBy{ it.puntajeCertificaciones() } //String
    fun puedeCubrir(ciudad: Ciudad) = vendedores.any{ it.puedeTrabajarEn(ciudad) } //Boolean
    fun vendedoresGenericos()  = vendedores.filter{ it.esGenerico() }.toSet() //Conjunto
    fun esRobusto() = vendedores.count{ it.esFirme() } >= 3 //Boolean

    //NO DEVUELVE NADA
    fun entregarCertificacion(certificacion: Certificacion){
        vendedores.forEach { it.agregarCertificacion(certificacion) }
    }
}