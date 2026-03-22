# AnkiInsight 💡

**AnkiInsight** is a high-performance AI diagnostic companion for **AnkiDroid**. It uses advanced Large Language Models (LLMs) to analyze your learning progress, fix failing cards, and discover knowledge gaps in your flashcard collection.

---

## 🌟 Key Features

### 1. 🧠 AI Failure Analyser (Groq Llama 3.1)
Don't just keep failing the same card. AnkiInsight identifies your "struggling" cards (low ease factor or repeated lapses) and uses the **Groq API** to:
*   **Diagnose the problem:** Understand why the current flashcard is difficult.
*   **Regenerate with Analogy:** Automatically rewrite the card using structured analogies to improve retention.
*   **Instant Sync:** Save the improved card back to your AnkiDroid collection with a single click.

### 2. 🔍 Concept Gap Detector
AI scans your failure history to find the "missing link." If you're failing cards about Photosynthesis, it might suggest you're missing a fundamental understanding of Chlorophyll and offer to generate a new explanatory card for you.

### 3. ⚔️ Cross-Deck Conflict Scanning
Find contradictory information across different decks. AnkiInsight scans your entire Anki ecosystem to find cards that might be teaching you conflicting facts, ensuring your mental model remains consistent.

---

## 🛠 Tech Stack
*   **Language:** Kotlin
*   **Architecture:** Clean Architecture + MVVM + Repository Pattern
*   **Dependency Injection:** Hilt
*   **Persistence:** Room Database (for local caching)
*   **AI Backend:** Groq Cloud API (Llama 3.1 8B Instant)
*   **Integration:** AnkiDroid Content Provider API

---

## 🚀 Getting Started

### Prerequisites
1.  **AnkiDroid** must be installed on your device.
2.  Enable **AnkiDroid API** in AnkiDroid Settings -> Advanced -> "Allow third-party API access".

### Installation & API Setup
1.  Clone the repository:
    ```bash
    git clone https://github.com/Ayush-Patel-56/AnkiInsight.git
    ```
2.  **Secure API Key Configuration:**
    This app uses the Groq API. To keep your keys safe, add your key to your `local.properties` file (which is excluded from Git):
    ```properties
    groq.api.key=your_groq_api_key_here
    ```
3.  Build and run the app!

---

## 🔒 Privacy & Security
AnkiInsight interacts directly with the AnkiDroid database.
*   **Local Processing:** All scan results and cached cards are stored locally in a Room database.
*   **No Data Leakage:** Your API keys are strictly stored in `local.properties` and are never committed to version control.

---

## 🤝 Contributing
Contributions are welcome! If you have ideas for new AI diagnostics or UI improvements, feel free to open a PR.

---

## 📄 License
This project is licensed under the MIT License.
