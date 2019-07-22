export default class ProductService {
    constructor($http, $q) {
        this._$http = $http;
        this._$q = $q;
    }

    getData() {
        let request = {
            method: 'GET',
            url: 'http://localhost:8080/SampleCRUD/product-list'
        };

        return this._$http(request).then((res) => {
           
            return res.data;
        });
    }

    getCategoryList() {
        let request = {
            method: 'GET',
            url: 'http://localhost:8080/SampleCRUD/category-list'
        };

        return this._$http(request).then((res) => {
           
            return res.data;
        });
    }

    delete(id){
        let request = {
            method: 'GET',
            url: 'http://localhost:8080/SampleCRUD/delete/'+id,
        };
        return this._$http(request);
    }

    update(product){
        let request = {
            method: 'POST',
            url: 'http://localhost:8080/SampleCRUD/submitProduct',
            data: JSON.stringify(product)
        }
        return this._$http(request).then((res) => {
            console.log('update obj >> ', res.data);
            return res.data
        });
    }

    edit(id){
        let request = {
            method: 'GET',
            url: 'http://localhost:8080/SampleCRUD/edit/'+id
        }
        return this._$http(request);
    }
}

ProductService.$inject = ['$http', '$q'];