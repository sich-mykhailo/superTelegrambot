package com.parser.util;

import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Emojis {
   SCROLL(EmojiParser.parseToUnicode(":scroll:")),
   BAR_CHART(EmojiParser.parseToUnicode(":bar_chart:")),
   SOS(EmojiParser.parseToUnicode(":sos:")),
   EMAIL(EmojiParser.parseToUnicode(":email:")),
   MAG(EmojiParser.parseToUnicode(":mag:")),
   CRY(EmojiParser.parseToUnicode(":cry:")),
   KEY(EmojiParser.parseToUnicode(":key:")),
   SUNGLASSES(EmojiParser.parseToUnicode(":sunglasses:")),
   ARROW_DOWN(EmojiParser.parseToUnicode(":arrow_down:")),
   LOADING(EmojiParser.parseToUnicode(":memo:"));

   private final String emojiName;

   @Override
    public String toString() {
       return emojiName;
   }
}
