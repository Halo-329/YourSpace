document.addEventListener('DOMContentLoaded', function() {
    const cartItemsContainer = document.querySelector('.cart-items');
    const totalPriceElement = document.querySelector('.total-price');
    const orderButton = document.querySelector('.order-button');

    // Dummy data for cart items
    const cartItems = [
        {
            id: 1,
            name: '멋진 신발',
            price: 75000,
            quantity: 1,
            imageUrl: 'https://placehold.co/120x120?text=Shoe'
        },
        {
            id: 2,
            name: '스타일리시한 셔츠',
            price: 45000,
            quantity: 2,
            imageUrl: 'https://placehold.co/120x120?text=Shirt'
        },
        {
            id: 3,
            name: '편안한 바지',
            price: 60000,
            quantity: 1,
            imageUrl: 'https://placehold.co/120x120?text=Pants'
        }
    ];

    function renderCartItems() {
        cartItemsContainer.innerHTML = '';
        let totalPrice = 0;

        cartItems.forEach(item => {
            const itemElement = document.createElement('div');
            itemElement.classList.add('cart-item');
            itemElement.innerHTML = `
                <img src="${item.imageUrl}" alt="${item.name}">
                <div class="item-details">
                    <h4>${item.name}</h4>
                    <p>${item.price.toLocaleString()}원</p>
                </div>
                <div class="item-quantity">
                    <input type="number" value="${item.quantity}" min="1" data-item-id="${item.id}">
                </div>
                <button class="remove-item-button" data-item-id="${item.id}">삭제</button>
            `;
            cartItemsContainer.appendChild(itemElement);
            totalPrice += item.price * item.quantity;
        });

        totalPriceElement.textContent = totalPrice.toLocaleString();
    }

    function handleCartActions(event) {
        const target = event.target;

        if (target.classList.contains('remove-item-button')) {
            const itemId = parseInt(target.dataset.itemId);
            // Add logic to remove item from cart
            console.log(`Remove item with ID: ${itemId}`);
        }

        if (target.tagName === 'INPUT') {
            const itemId = parseInt(target.dataset.itemId);
            const newQuantity = parseInt(target.value);
            // Add logic to update item quantity
            console.log(`Update quantity for item ID: ${itemId} to ${newQuantity}`);
        }
    }

    renderCartItems();

    cartItemsContainer.addEventListener('click', handleCartActions);

    orderButton.addEventListener('click', function() {
        // Add order processing logic
        alert('주문이 완료되었습니다.');
    });
});
