# ItemHighlight
- 是螢光劑 我加了螢光劑
  * 自動抓item displayName影響最多字的顏色（其實沒測試過）
- 實體名字會變道具ㄉ名字
- ~~沒名子的話會自動翻譯 雖然要LocaleLib~~
  * ~~翻譯是看客戶端選甚麼語言喔喔喔喔喔~~ nms branch 上那個才有這功能

# branches
## master
沒有辦法叫玩家自己翻譯，但是支援更多版本

## nms
有辦法叫玩家自己翻譯，但是只會在 1.16.5 上跑 (也許可能應該大概有機會 1.16.4 也能跑)\
如果哪天 Bugkit 更新，不會很智障的自己亂翻譯 (見下面 "幹你娘bugkit")\
可能會被幹回 master\
*我哪天可能會加多版本 但是不要指望*

### 幹你娘bugkit
```
幹你娘bugkit
setCustomName() 會很智障的跑一個超肥的method把他轉成nms看得懂的東西 `CraftChatMessage.fromStringOrNull(name)`
然後那個method會很智障的把translate tag當成超連結
所以我只好手動幹 繞過那個垃圾函式直接幹上正確的東西
```
