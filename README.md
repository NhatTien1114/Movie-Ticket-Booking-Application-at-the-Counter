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
2. Open Eclipse → File → Import → Existing Projects into Workspace

3. Configure MySQL connection in DatabaseConfig.java

4. Run the main class:
   ```bash
   UI.MainFrame.java


5. Start booking tickets right from the counter!

📩 Example Email Output

Subject: Cinema Ticket Confirmation
Body:

Thank you for your purchase!
Movie: Avengers: Endgame
Showtime: 19:00 - Room 3
Seat: B5, B6
Total: 250,000 VND
QR Code attached for entry verification.

🧑‍💻 Author

Tống Nguyễn Nhật Tiến
Email: tongnguyennhattien140805@gmail.com

Project Type: Java Swing Application
Purpose: Cinema Counter Ticket Booking System

📜 License

This project is developed for educational purposes and may be extended for commercial use.
