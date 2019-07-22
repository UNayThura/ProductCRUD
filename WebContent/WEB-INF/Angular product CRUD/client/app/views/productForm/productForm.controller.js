import { $IsStateFilter } from "angular-ui-router/lib/stateFilters";

class ProductFormController {


  constructor(ProductFormService,$rootScope,$scope,$state) {
    this.ProductFormService = ProductFormService
    this.obj = '';
    this.productName = '';
    this.productQuantity = '';
    this.productPrice = '';
    this.categoryName = '';
    this.newCategoryName = '';
    this.$state = $state;
    this.length = 0 ;
    this.ProductFormService.getCategoryList().then(function(response){
      
      this.categoryList=response

    }.bind(this))
   
  }

  submit(){
  
     let obj = {
      name: this.productName,
      quantity: this.productQuantity,
      price: this.productPrice,
      categoryName: this.categoryName,
      editing: false
    }
    if(this.categoryName == ''){
      console.log('invalid selected')
    }else{
      let productObj = this.ProductFormService.create(obj);
    productObj.then(() => {
      this.$state.go('productList');
    })
    this.productName = '';
    this.productQuantity = '';
    this.productPrice = '';
    obj = null;
    }
    
  }

  createCategory(){
    let createObj = this.ProductFormService.createCategoryService(this.newCategoryName);
    this.newCategoryName = '';
    createObj.then((response)=> {
      if(response.id == 0){
        
        this.newCategoryName = response.name;
        this.length = 1;
      }else{

        this.ProductFormService.getCategoryList().then(function(response){
      
          this.categoryList=response
          console.log('category list >> ', this.categoryList)
         }.bind(this))
      }
    });
    this.length=0
    
  }  
}

ProductFormController.$inject = ['ProductFormService', '$rootScope','$scope','$state'];
export default ProductFormController;