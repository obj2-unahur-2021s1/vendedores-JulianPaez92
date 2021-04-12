package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  val cordoba = Provincia(2000000)
  val villaDolores = Ciudad(cordoba)

  val tokio = Provincia(9000000)
  val shinjuku = Ciudad(tokio)

  val certificacionProducto = Certificacion(true, 9)
  val certificacionNoProducto = Certificacion(false, 10)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)
    val vendedorFijo2 = VendedorFijo(obera)


    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
    describe("esInfluyente"){
      it("vendedorFijo no es influyente"){
        vendedorFijo.esInfluyente().shouldBeFalse()
      }
    }
    describe("esVersatil"){
      vendedorFijo.agregarCertificacion(certificacionProducto)
      vendedorFijo.agregarCertificacion(certificacionProducto)
      vendedorFijo.agregarCertificacion((certificacionNoProducto))
      vendedorFijo2.agregarCertificacion(certificacionNoProducto)
      vendedorFijo2.agregarCertificacion(certificacionNoProducto)
      vendedorFijo2.agregarCertificacion(certificacionNoProducto)
      it("vendedorFijo tiene al menos 1 certificacion de producto y 1 de no producto, y al menos 3 certificaciones"){
        vendedorFijo.esVersatil().shouldBeTrue()
      }
      it("vendedorFijo2 no tiene certificaciones de producto"){
        vendedorFijo2.esVersatil().shouldBeFalse()
      }
    } //que tenga al menos tres certificaciones, que tenga al menos una sobre productos, y al menos una que no sea sobre productos.

    describe("esFirme"){
      it("vendedorFijo2 tiene un puntaje en certificaciones igual o mayor a 30"){
        vendedorFijo2.esFirme().shouldBeTrue()
      }
      it("vendedorFijo no tiene un puntaje igual o mayor a 30 en certificaciones"){
        vendedorFijo.esFirme().shouldBeFalse()
      }
    }

  }

  describe("Viajante") {
    val viajante = Viajante(listOf(misiones))
    val viajante2 = Viajante(listOf(cordoba,tokio))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }

    describe("esInfluyente"){
      it("viajante2 es influyente"){
        viajante2.esInfluyente().shouldBeTrue()
      }
      it("viajante no es influyente"){
        viajante.esInfluyente().shouldBeFalse()
      }
    }

    describe("esVersatil"){
      viajante.agregarCertificacion(certificacionProducto)
      viajante.agregarCertificacion(certificacionProducto)
      viajante.agregarCertificacion((certificacionNoProducto))
      viajante2.agregarCertificacion(certificacionNoProducto)
      viajante2.agregarCertificacion(certificacionNoProducto)
      viajante2.agregarCertificacion(certificacionNoProducto)
      it("viajante es versatil"){
        viajante.esVersatil().shouldBeTrue()
      }
      it("viajante2 no es versatil"){
        viajante2.esVersatil().shouldBeFalse()
      }
    } //que tenga al menos tres certificaciones, que tenga al menos una sobre productos, y al menos una que no sea sobre productos.

    describe("esFirme"){
      it("viajante2 tiene un puntaje en certificaciones igual o mayor a 30"){
        viajante2.esFirme().shouldBeTrue()
      }
      it("viajante no tiene un puntaje igual o mayor a 30 en certificaciones"){
        viajante.esFirme().shouldBeFalse()
      }
    }
  }

  describe("Comercio corresponsal") {
    val ciudad1 = Ciudad(misiones)
    val ciudad2 = Ciudad(misiones)
    val ciudad3 = Ciudad(misiones)
    val ciudad4 = Ciudad(misiones)
    val ciudad5 = Ciudad(misiones)
    val ciudad6 = Ciudad(cordoba)
    val ciudad7 = Ciudad(tokio)

    val comercio1 = ComercioCorresponsal(listOf(ciudad1,ciudad2,ciudad3,ciudad4,ciudad5))
    val comercio2 = ComercioCorresponsal(listOf(ciudad1))
    val comercio3 = ComercioCorresponsal(listOf(ciudad1,ciudad6,ciudad7))
    describe("puedeTrabajarEn") {
      it("comercio1 tiene una sucursal en la ciudad ciudad3"){
        comercio1.puedeTrabajarEn(ciudad3).shouldBeTrue()
      }
      it("comercio1 no tiene una sucursal en la ciudad sanIgnacio"){
        comercio1.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }

    describe("esInfluyente"){
      it("comercio1 tiene al menos 5 sucursales"){
        comercio1.esInfluyente().shouldBeTrue()
      }
      it("comercio3 tiene al menos 3 provincias diferentes"){
        comercio3.esInfluyente().shouldBeTrue()
      }
      it("comercio2 no tiene ni 5 sucursales"){
        comercio2.esInfluyente().shouldBeFalse()
      }
    }

    describe("esVersatil"){
      comercio1.agregarCertificacion(certificacionProducto)
      comercio1.agregarCertificacion(certificacionProducto)
      comercio1.agregarCertificacion((certificacionNoProducto))
      comercio2.agregarCertificacion(certificacionNoProducto)
      comercio2.agregarCertificacion(certificacionNoProducto)
      comercio2.agregarCertificacion(certificacionNoProducto)
      it("comercio1 tiene al menos 1 certificacion de producto y 1 de no producto, y al menos 3 certificaciones"){
        comercio1.esVersatil().shouldBeTrue()
      }
      it("comercio2 no tiene certificaciones de producto"){
        comercio2.esVersatil().shouldBeFalse()
      }
    } //que tenga al menos tres certificaciones, que tenga al menos una sobre productos, y al menos una que no sea sobre productos.

    describe("esFirme"){
      it("comercio2 tiene un puntaje en certificaciones igual o mayor a 30"){
        comercio2.esFirme().shouldBeTrue()
      }
      it("comercio1 no tiene un puntaje igual o mayor a 30 en certificaciones"){
        comercio1.esFirme().shouldBeFalse()
      }
    }
  }
})
