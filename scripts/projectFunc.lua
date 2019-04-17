function _onAttack(user, target, attack)
  local dmg = dmgMod(user, target, attack)
  target.health = target.health - damage
  return target
end

function _dmgMod(user, target, attack)
  local action = true
  local atkBuff = _typeMod(target, attack, action)
  local atkDmg = atkBuff + attack.Dmg + critDmg(user)
  local def = _defMod(target, attack)
  return atkDmg - def
end

function _defMod(target, attack)
  local action = false
  local defBuff = _typeMod(target, attack, action)
  return target.def + defBuff
end

function _typeMod(target, attack, action)
  local isWeak = _isWeak(target, attack)
  if action == true then
    if isWeak == true then return _typeBuff(target, attack, action)
  else return 0 end
  
  elseif action == false then
    if isWeak == false then return _typeBuff(target, attack, action)
  else return 0 end end
end

function _critDmg(luck)
  count = 0
  for i=1,luck do
    if math.random(100) >= 95 then count = count + 1 end
  end return count  
end

function _isWeak(target, attack, typeDictionary)
  -- Need to figure out what's gonna be happening ; Use dicitionary to return true, false or nil based
  -- on if an attack is effective or not
end

function _typeBuff(target, attack, action)
  -- If an attack is effective this is where the atkBuff will add on some attack points, 
  -- and if it's really not effective then the defBuff will be scaled up
end
