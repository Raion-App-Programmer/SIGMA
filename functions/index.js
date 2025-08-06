const functions = require("firebase-functions");
const admin = require("firebase-admin");
const nodemailer = require("nodemailer");

// Initialize Firebase Admin SDK
admin.initializeApp();

// Configure your email service
const transporter = nodemailer.createTransport({
  service: "gmail",
  auth: {
    user: "femnitality@gmail.com",  // Replace with your email
    pass: "hklmmgcypmkvxksk"    // Replace with your email password or app password
  }
});

// Generate a random 6-digit OTP
const generateOTP = () => Math.floor(100000 + Math.random() * 900000).toString();

// Cloud function to send OTP
exports.sendOtp = functions.https.onRequest(async (req, res) => {
  const { email } = req.body;
  if (!email) return res.status(400).send("Email is required.");

  const otp = generateOTP();
  
  // Store OTP in Firebase Firestore (optional)
  await admin.firestore().collection("otps").doc(email).set({
    otp,
    createdAt: admin.firestore.FieldValue.serverTimestamp()
  });

  // Send OTP via email
  const mailOptions = {
    from: "femnitality@gmail.com",
    to: email,
    subject: "Your OTP Code",
    text: `Your OTP code is: ${otp}`
  };

  try {
    await transporter.sendMail(mailOptions);
    res.status(200).send("OTP sent successfully.");
  } catch (error) {
    console.error(error);
    res.status(500).send("Failed to send OTP.");
  }
});
