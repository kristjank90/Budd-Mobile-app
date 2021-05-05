package com.nfcpay.budd
// Data model for a Basket transaction (TODO) Delete this model as not in use
data class TransactionModel(
    var transactionId:Int?=0,
    var customerId:Int?=0,
    var customerName:String?="",
    var itemList:ArrayList<Item>?,
    var VAT:Number?=0,
    var basketVAT:Number?=0,
    var basketSum:Number?=0,
    var totalSum:Number?=0,
    var status:String?="",
    var currency:String?=""
)

data class Item(
    var name:String?="",
    var price:Number?=0,
    var description:String?="",
    var currency:String?="",
    var amount:Int?=0
)