let app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function ($scope, $http) {
    // alert("AngularJS initialized")
    /*
    * Quản lý giỏ hàng
     */
    $scope.cart = {
        items: [],
        // Thêm sản phẩm vào giỏ hàng
        add(id) {
            // alert(id)
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
            console.log();
        },

        // Xoa san pham
        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },

        // xoa sach cac san pham
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        // tinh thanh tien cua 1 san pham
        amt_of(item) {
        },

        // tinh tong cac mat hang trong gio
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },

        // tinh thanh tien cac mat hang trong gio
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },

        // luu gio hang vao local storage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },

        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }
    $scope.cart.loadFromLocalStorage();

    $scope.order = {
        createDate: new Date(),
        address: "",
        account: {username: $("#username").text()},
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: {id: item.id},
                    price: item.price,
                    quantity: item.qty
                }
            })
        },
        purchase() {
            var order = angular.copy(this);

            // thuc hien dat hang
            $http.post("/rest/orders", order).then(resp => {
                alert("Purchased");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                alert("Failed order !");
                console.log(error);
            })
            // alert("Ok roi do")
        }
    }
})