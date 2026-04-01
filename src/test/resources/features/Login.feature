# language: es
Característica: Inicio de sesión en SauceDemo
  Como usuario de la tienda
  Quiero iniciar sesión con mis credenciales
  Para poder comprar productos

  Escenario: Login exitoso con usuario estándar
    Dado que estoy en la página de inicio de sesión de SauceDemo
    Cuando ingreso el usuario "standard_user" y la contraseña "secret_sauce"
    Entonces debería ver el panel principal de productos