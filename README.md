# 🎬 On-site Cinema Ticket Booking Application

## 📖 Overview
This is a **desktop-based Java Swing application** that supports cinema staff in selling movie tickets **directly at the counter**.  
The system provides an intuitive interface to manage movies, showtimes, seats, food combos, and payments efficiently.

---

## ⚙️ Key Features

### 🎥 Movie & Showtime Management
- View and select movies currently showing.
- Choose specific showtimes and screening rooms.

### 💺 Seat Selection
- Interactive seat layout.
- Supports selecting, unselecting, and counting seats dynamically.

### 🍿 Food & Drink Combos
- Display available combos with images and prices.
- Add or remove combos from an order.
- Automatically update total cost.

### 💵 Payment & QR Code Integration
- Calculate the total ticket and combo price automatically.
- Generate a **QR code** for each completed transaction.
- The QR code can be scanned for quick payment verification or ticket check-in.

### 📧 Email Confirmation
- After successful payment, the system **sends ticket details and QR code** directly to the customer’s email.
- Email includes movie name, showtime, seat number, total cost, and attached QR image.

### 🧾 Invoice & History
- Generate and print invoices.
- Store transaction data for reporting and lookup.

---

## 🏗️ Technologies Used
- **Java Swing** – GUI design  
- **JavaMail API** – Sending confirmation emails  
- **ZXing Library** – Generating QR codes  
- **MySQL** – Database management  
- **JDBC** – Database connection  
- **AWT & Swing Components** – Interface styling  

---

## 🚀 How to Run
1. Clone this repository to your Eclipse workspace:
   ```bash
   git clone https://github.com/yourusername/CinemaTicketApp.git
