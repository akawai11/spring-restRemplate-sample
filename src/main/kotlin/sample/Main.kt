package sample

fun main(args: Array<String>) {

    val springRestTemplate = SpringRestTemplate()
    System.out.println(springRestTemplate.getForObject())
    System.out.println(springRestTemplate.getForObjectWithParam())
    System.out.println(springRestTemplate.exchangeGet())
    System.out.println(springRestTemplate.postForObject())
    System.out.println(springRestTemplate.exchangePost())

}

