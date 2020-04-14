package com.carolinarollergirls.scoreboard.event;

public class TestScoreBoardEventProviderImpl extends ScoreBoardEventProviderImpl<TestScoreBoardEventProvider>
        implements TestScoreBoardEventProvider {
    public TestScoreBoardEventProviderImpl() {
        super(null, "", null);
        setupReferences();
    }
    public TestScoreBoardEventProviderImpl(TestScoreBoardEventProvider parent, String id,
            AddRemoveProperty<TestScoreBoardEventProvider> type) {
        super(parent, id, type);
        setupReferences();
    }

    private void setupReferences() {
        addProperties(INT, RO_INDIRECT_COPY, RW_INDIRECT_COPY, RECALCULATED, REFERENCE, MULTIPLE, SINGLETON, NUMBERED,
                TEST_COMMAND);
        setInverseReference(MULTIPLE, REFERENCE);
        setInverseReference(REFERENCE, MULTIPLE);
        setCopy(RO_INDIRECT_COPY, this, REFERENCE, INT, true);
        setCopy(RW_INDIRECT_COPY, this, REFERENCE, INT, false);
        if (parent == null) { add(SINGLETON, new TestScoreBoardEventProviderImpl(this, "", SINGLETON)); }
        addWriteProtection(SINGLETON);
        setRecalculated(RECALCULATED).addSource(this, INT).addIndirectSource(this, REFERENCE, INT);
    }

    @Override
    protected Object computeValue(PermanentProperty<?> prop, Object value, Object last, Source source, Flag flag) {
        valuesRecalculated++;
        if (prop == RECALCULATED) {
            return -(Integer) value;
        }
        return value;
    }
    @Override
    protected void valueChanged(PermanentProperty<?> prop, Object value, Object last, Source source, Flag flag) {
        valuesChanged++;
    }

    @Override
    protected void itemAdded(AddRemoveProperty<?> prop, ValueWithId item, Source source) {
        itemsAdded++;
    }
    @Override
    protected void itemRemoved(AddRemoveProperty<?> prop, ValueWithId item, Source source) {
        itemsRemoved++;
    }

    @Override
    public void execute(CommandProperty prop, Source source) {
        commmandsExecuted++;
    }

    protected int valuesRecalculated = 0;
    protected int valuesChanged = 0;
    protected int itemsAdded = 0;
    protected int itemsRemoved = 0;
    protected int commmandsExecuted = 0;
}
