
force read master database
```
User user = null;

HintManager.clear();
try (HintManager hintManager = HintManager.getInstance()) {
    hintManager.setMasterRouteOnly();
    user = userMapper.getUserById(id);
}
return user;
```

read master database
http://127.0.0.1:7312/api/v1/user/db1/1

read slave database
http://127.0.0.1:7312/api/v1/user/db2/2
