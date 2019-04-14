package com.raiden.domain.interactors.info.commands

import com.raiden.domain.gateways.ApplicationsGateway

class GetCountOfDeletedApps(gateway: ApplicationsGateway){
    fun getCountOfDeletedApps(): Int{
       return -1
    }
}