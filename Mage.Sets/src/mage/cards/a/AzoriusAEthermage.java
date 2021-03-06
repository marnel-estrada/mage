/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
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
 */
package mage.cards.a;

import mage.MageInt;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.filter.FilterPermanent;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.game.permanent.Permanent;

import java.util.UUID;

/**
 *
 * @author jeffwadsworth
 */
public class AzoriusAEthermage extends CardImpl {

    private static final String rule = "Whenever a permanent is returned to your hand, ";

    public AzoriusAEthermage(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{W}{U}");

        this.subtype.add("Human");
        this.subtype.add("Wizard");
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // Whenever a permanent is returned to your hand, you may pay {1}. If you do, draw a card.
        Effect effect = new DoIfCostPaid(new DrawCardSourceControllerEffect(1), new ManaCostsImpl("{1}"));
        this.addAbility(new AzoriusAEthermageAbility(Zone.BATTLEFIELD, Zone.BATTLEFIELD, Zone.HAND, effect, new FilterPermanent(), rule, true));
    }

    public AzoriusAEthermage(final AzoriusAEthermage card) {
        super(card);
    }

    @Override
    public AzoriusAEthermage copy() {
        return new AzoriusAEthermage(this);
    }
}

class AzoriusAEthermageAbility extends TriggeredAbilityImpl {

    protected FilterPermanent filter;
    protected Zone fromZone;
    protected Zone toZone;
    protected String rule;

    public AzoriusAEthermageAbility(Zone zone, Zone fromZone, Zone toZone, Effect effect, FilterPermanent filter, String rule, boolean optional) {
        super(zone, effect, optional);
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.rule = rule;
        this.filter = filter;
    }

    public AzoriusAEthermageAbility(final AzoriusAEthermageAbility ability) {
        super(ability);
        this.fromZone = ability.fromZone;
        this.toZone = ability.toZone;
        this.rule = ability.rule;
        this.filter = ability.filter;
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ZONE_CHANGE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        ZoneChangeEvent zEvent = (ZoneChangeEvent) event;
        if ((fromZone == null || zEvent.getFromZone() == fromZone)
                && (toZone == null || zEvent.getToZone() == toZone)) {
            Permanent permanentThatMoved = null;
            if (zEvent.getTarget() != null) {
                permanentThatMoved = zEvent.getTarget();
            }
            if (permanentThatMoved != null
                    && filter.match(permanentThatMoved, sourceId, controllerId, game)
                    && zEvent.getPlayerId() == controllerId) { //The controller's hand is where the permanent moved to.
                return true;
            }
        }
        return false;
    }

    @Override
    public String getRule() {
        return rule + super.getRule();
    }

    @Override
    public AzoriusAEthermageAbility copy() {
        return new AzoriusAEthermageAbility(this);
    }

    public Zone getFromZone() {
        return fromZone;
    }

    public Zone getToZone() {
        return toZone;
    }
}
