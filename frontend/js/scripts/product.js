document.addEventListener("DOMContentLoaded", () => {
    getProducts();
});

const token = localStorage.getItem("token");

async function getProducts() {
    try {
        const response = await fetch("http://localhost:8080/product", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "application/json"
            }
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || "Failed to find products");
        }

        const products = await response.json();
        const container = document.getElementById("product-cards");

        products.forEach(product => {
            const card = document.createElement("div");
            card.className = "product-card";
            card.innerHTML = `
            <img src="http://localhost:8080/product/${product.id}/image" alt="${product.productName}" class="product-card-image">
            <h3 class="product-name">${product.productName}</h3>
            <p class="product.description">${product.description}</p>
            <p class="product-price"><strong>${product.price}<strong></p>
            <button type="submit" class="product-card-button">Add to order</button>
            `;

            container.appendChild(card);
        });

    } catch(error) {
        console.error("error on load products: ", error);
    }
}



//<img src="${product.image}" alt="${product.name}" class="product-image">