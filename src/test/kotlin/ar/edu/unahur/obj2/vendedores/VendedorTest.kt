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

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }

    describe("esInfluyente"){
      it("No lo es"){
        vendedorFijo.esInfluyente().shouldBeFalse()
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
      it("tiene 10 millones de habitantes al menos"){
        viajante2.esInfluyente().shouldBeTrue()
      }
      it("NO tiene mas de 10 millones de habitantes"){
        viajante.esInfluyente().shouldBeFalse()
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
      it("tiene una sucursal en la ciudad"){
        comercio1.puedeTrabajarEn(ciudad3).shouldBeTrue()
      }
      it("NO tiene una sucursal en la ciudad"){
        comercio1.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }

    describe("esInfluyente"){
      it("tiene al menos 5 sucursales"){
        comercio1.esInfluyente().shouldBeTrue()
      }
      it("tiene al menos 3 provincias diferentes"){
        comercio3.esInfluyente().shouldBeTrue()
      }
      it("No tiene ni 5 sucursales"){
        comercio2.esInfluyente().shouldBeFalse()
      }
    }
  }
})
