export default class ProductFormService{

    'ngInject';
    constructor($http,$q){
        this.$http = $http;
        this.$q = $q;
    }

    getCategoryList() {
        let request = {
            method: 'GET',
            url: 'http://localhost:8080/SampleCRUD/category-list'
        };

        return this.$http(request).then((res) => {
           
            return res.data;
        });
    }

    create(product){
        let request = {
            method: 'POST',
            url: 'http://localhost:8080/SampleCRUD/productForm',
            data: JSON.stringify(product)
        }

        return this.$http(request).then((res) => {
            console.log('res.data >> ', res.data)
            return res.data;
        });
    }

    createCategoryService(str){
        let request = {
            method: 'POST',
            url: 'http://localhost:8080/SampleCRUD/category',
            data: str
        }
        return this.$http(request).then(res => {
            return res.data;
        })
    }


}

ProductFormService.$inject = ['$http', '$q'];