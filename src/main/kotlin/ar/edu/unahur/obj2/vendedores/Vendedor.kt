package ar.edu.unahur.obj2.vendedores

class Certificacion(val esDeProducto: Boolean, val puntaje: Int)

abstract class Vendedor {
  // Acá es obligatorio poner el tipo de la lista, porque como está vacía no lo puede inferir.
  // Además, a una MutableList se le pueden agregar elementos
  val certificaciones = mutableListOf<Certificacion>()

  // Definimos el método abstracto.
  // Como no vamos a implementarlo acá, es necesario explicitar qué devuelve.
  abstract fun puedeTrabajarEn(ciudad: Ciudad): Boolean

  // En las funciones declaradas con = no es necesario explicitar el tipo
  fun esVersatil() =
    certificaciones.size >= 3
      && this.certificacionesDeProducto() >= 1
      && this.otrasCertificaciones() >= 1

  // Si el tipo no está declarado y la función no devuelve nada, se asume Unit (es decir, vacío)
  //NO DEVUELVE NADA
  fun agregarCertificacion(certificacion: Certificacion) {
    certificaciones.add(certificacion)
  }

  fun esFirme()= this.puntajeCertificaciones() >= 30 //Boolean

  fun certificacionesDeProducto() = certificaciones.count { it.esDeProducto } //Int
  fun otrasCertificaciones() = certificaciones.count { !it.esDeProducto } //Int

    fun puntajeCertificaciones() = certificaciones.sumBy { c -> c.puntaje } //Int
  //ETAPA 2
  abstract fun esInfluyente(): Boolean
  //ETAPA 3
  fun esGenerico() = this.otrasCertificaciones() > 0 //Boolean
}

// En los parámetros, es obligatorio poner el tipo
class VendedorFijo(val ciudadOrigen: Ciudad) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return ciudad == ciudadOrigen
  }
  //ETAPA 2
  override fun esInfluyente() = false //Boolean
}

// A este tipo de List no se le pueden agregar elementos una vez definida
class Viajante(val provinciasHabilitadas: List<Provincia>) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return provinciasHabilitadas.contains(ciudad.provincia)
  }
  //ETAPA 2
  override fun esInfluyente() = this.provinciasHabilitadas.sumBy { it.poblacion } >= 10000000 //Boolean
  //  override fun esInfluyente() = this.provinciasHabilitadas.sumBy { it.poblacion } >= 10000000 //Boolean
}

class ComercioCorresponsal(val ciudades: List<Ciudad>) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return ciudades.contains(ciudad)
  }
  //ETAPA 2
  fun ciudadesConSucursales() = ciudades.toSet().size //Int
  fun provinciasConSucursales() = ciudades.map { it.provincia }.toSet().size //Int
  override fun esInfluyente() = (this.ciudadesConSucursales() >= 5) or (this.provinciasConSucursales() >= 3) //Boolean
}
