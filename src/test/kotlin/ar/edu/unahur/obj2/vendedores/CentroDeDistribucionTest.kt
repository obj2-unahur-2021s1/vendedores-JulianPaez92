package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.*
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe

class CentroDeDistribucionTest : DescribeSpec({
    //Provincias con ciudades
    val tokio = Provincia(9000000)
    val shinjuku = Ciudad(tokio)
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    //certificaciones
    val certificacionProducto = Certificacion(true, 11)
    val certificacionNoProducto = Certificacion(false, 10)
    //vendedores
    val vendedor1 = VendedorFijo(shinjuku)
    val vendedor2 = VendedorFijo(shinjuku)
    val vendedor3 = VendedorFijo(shinjuku)
    //agrega certificaciones a los vendedores
    vendedor1.agregarCertificacion(certificacionProducto)
    vendedor1.agregarCertificacion(certificacionProducto)
    vendedor1.agregarCertificacion(certificacionProducto)
    vendedor2.agregarCertificacion(certificacionNoProducto)
    vendedor2.agregarCertificacion(certificacionNoProducto)
    vendedor2.agregarCertificacion(certificacionNoProducto)
    vendedor3.agregarCertificacion(certificacionNoProducto)
    vendedor3.agregarCertificacion(certificacionNoProducto)
    vendedor3.agregarCertificacion(certificacionNoProducto)
    //centros de distribucion
    val centroDeDistribucion1 = CentroDeDistribucion(shinjuku)
    val centroDeDistribucion2 = CentroDeDistribucion(shinjuku)
    //agrega vendedores a los centros
    centroDeDistribucion1.agregarVendedor(vendedor1)
    centroDeDistribucion1.agregarVendedor(vendedor2)
    centroDeDistribucion1.agregarVendedor(vendedor3)
    centroDeDistribucion2.agregarVendedor(vendedor1)
    centroDeDistribucion2.agregarVendedor(vendedor2)

    describe("vendedorEstrella"){
        it("vendedor1 tiene el mayor puntaje"){
            centroDeDistribucion1.vendedorEstrella().shouldBe(vendedor1)
        }
        it("vendedor2 no tiene el mayor puntaje"){
            centroDeDistribucion1.vendedorEstrella().shouldNotBe(vendedor2)
        }
    }
    describe("puedeCubrir"){
        it("shinjuku no puede ser cubierto"){
            centroDeDistribucion1.puedeCubrir(shinjuku).shouldBeTrue()
        }
        it("villaDolores no puede ser cubierto"){
            centroDeDistribucion1.puedeCubrir(villaDolores).shouldBeFalse()
        }
    }
    describe("vendedoresGenericos"){
        it("vendedor2 y vendedor3 de genericos en el centro"){
            centroDeDistribucion1.vendedoresGenericos().shouldContainAll(vendedor2,vendedor3)
        }
        it("vendedor1 no es vendedor generico"){
            centroDeDistribucion1.vendedoresGenericos().shouldNotContain(vendedor1)
        }
    }
    describe("esRobusto"){
        it("el centroDeDistribucion1 tiene 3 vendedores firmes"){
            centroDeDistribucion1.esRobusto().shouldBeTrue()
        }
        it("el centroDeDistribucion2 no tiene 3 vendedores firmes"){
            centroDeDistribucion2.esRobusto().shouldBeFalse()
        }
    }
})

