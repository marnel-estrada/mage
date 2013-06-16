/*
 * 
 * Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 *
 */
package mage.sets.championsofkamigawa;

import java.util.UUID;

import mage.constants.CardType;
import mage.constants.Rarity;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.cards.CardImpl;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.WasDealtDamageThisTurnPredicate;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author LevelX
 */
public class CrushingPain extends CardImpl<CrushingPain> {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("creature that was dealt damage this turn");

    static {
        filter.add(new WasDealtDamageThisTurnPredicate());
    }

    public CrushingPain (UUID ownerId) {
        super(ownerId, 162, "Crushing Pain", Rarity.COMMON, new CardType[]{CardType.INSTANT}, "{1}{R}");
        this.expansionSetCode = "CHK";
        this.color.setRed(true);

        // Crushing Pain deals 6 damage to target creature that was dealt damage this turn.
        this.getSpellAbility().addEffect(new DamageTargetEffect(6));
        this.getSpellAbility().addTarget(new TargetCreaturePermanent(filter));
    }

    public CrushingPain (final CrushingPain card) {
        super(card);
    }

    @Override
    public CrushingPain copy() {
        return new CrushingPain(this);
    }

}


