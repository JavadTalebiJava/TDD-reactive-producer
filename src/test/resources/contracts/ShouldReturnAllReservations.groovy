package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.http.MediaType

Contract.make()  {
    description("/reservations")
    request {
        url("/reservations")
        method(HttpMethods.GET)
    }
    response {
        status(200)
        body(
                [
                    [id:1,name:"Sinan"],
                    [id:2,name:"Unal"]
                ]
        )
        headers { contentType(MediaType.APPLICATION_JSON_VALUE)}
    }
}
