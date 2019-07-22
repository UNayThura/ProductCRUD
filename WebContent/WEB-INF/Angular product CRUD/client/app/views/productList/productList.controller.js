import { isBuffer } from "util";
import { forEach } from "@uirouter/core";
import { throws } from "assert";

class ProductListController {

  'ngInject';
  constructor(ProductService, $rootScope, $http) {
    this.name = 'porudct';
    this.productName = ''
    this.productQuantity = '';
    this.productPrice = '';
    this.productCategoryName = ''
    this.productList = [];
    this.categoryList = [];
    $rootScope.productList = [];
    this.$http=$http;
    this.ProductService = ProductService;
    this.searchVal = '';
    ProductService.getData().then(function (response) {
      this.productList = response
      
      $rootScope.productList = this.productList
      
    }.bind(this))

    ProductService.getCategoryList().then(function(response) {
      this.categoryList = response;
    
    }.bind(this));
    
  }

  deleteProduct(productId){

    var alertText = confirm('Are you sure to do that?');
    if(alertText == true){

      this.ProductService.delete(productId);
      this.productList = this.productList.filter( product => product.id !== productId);
    }else(

      console.log("press cancle") 
    )
  }

  editProduct(productId){

    this.productList.map(product=>{
      if(product.editing) !product.editing 
      

      if(product.id == productId){
        product.editing = true;
        this.productName = product.name;
        this.productQuantity = product.quantity;
        this.productPrice = product.price;
        this.productCategoryName = product.categoryName;
      }
    })
    
  }

  cancelProduct(productId){

    for(let i = 0; i< this.productList.length; i++){
      if(this.productList[i].id == productId){
        this.productName = this.productList[i].name;
        this.productQuantity = this.productList[i].quantity;
        this.productPrice = this.productList[i].price;
        this.productCategoryName = this.productList[i].categoryName;
      }
    }
  }

  saveProduct(productId, index){
    
    if(this.productName == '' ||  this.productQuantity < 0 || this.productPrice < 50 || this.productCategoryName == ''){

        this.productList[index].editing = true;
      }else{
        this.productList[index].editing = false;
        for (let i = 0; i < this.productList.length; i++) {
          if(this.productList[i].id == productId){
            console.log("category Name >>> ", this.productList[i].categoryName)
            
            let product = {
              id: productId,
              name: this.productName,
              quantity: this.productQuantity,
              price: this.productPrice,
              categoryId: this.productList[i].categoryId,
              categoryName: this.productList[i].categoryName
            }
            
            let updateObj = this.ProductService.update(product);
            updateObj.then((response) => {
              for (let i = 0; i < this.productList.length; i++) {
                if(this.productList[i].id == response.id){

                  let obj = {
                    id: response.id,
                    name: response.name,
                    price: response.price,
                    quantity: response.quantity,
                    categoryId: response.category.id,
                    categoryName: response.category.name
                  }

                  this.productList[index] = obj;
                }
              }
            })
          }
        }
      }
  }

  clear(){
    this.searchVal = '';
  }

}

ProductListController.$inject = ['ProductService', '$rootScope', '$http'];

export default ProductListController;
